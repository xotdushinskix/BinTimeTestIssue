package com.bintime.hatch.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bintime.hatch.helper.MultipartUploadHelper;
import com.bintime.hatch.model.TextFile;
import com.bintime.hatch.service.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class FileUploadController {

	@Autowired
	private MultipartUploadHelper multipartUploadHelper;

	@Autowired
	private TextFileService textFileService;



	@RequestMapping(value = "/words", method = RequestMethod.GET)
	public ResponseEntity<List<TextFile>> allFiles() throws SQLException {
		List<TextFile> files = textFileService.getAllTextFile();
		if (files.isEmpty()) {
			return new ResponseEntity<List<TextFile>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TextFile>>(files, HttpStatus.OK);
	}



	@RequestMapping(value = "/upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files)
			throws IOException, SQLException {

		final List<File> converterFiles = multipartUploadHelper.multiFileConverter(files);
		final ExecutorService exec = Executors.newFixedThreadPool(3);  											// the beginning of triple parallel process

		exec.submit(new Runnable() {
			@Override
			public void run() {
				for (File readAbleFile : converterFiles) {														// look over all responsed text files
					try (BufferedReader br = new BufferedReader(new FileReader(readAbleFile))) {				// read each text file
						String line = null;
						while ((line = br.readLine()) != null) {
							Map<String, Integer> staff = multipartUploadHelper.stringCounter(readAbleFile);		// get map with word + the number of repeats (key:value)

							for (Map.Entry entry : staff.entrySet()) {											// look over each word and get his info
								String key = (String) entry.getKey();
								int value = (int) entry.getValue();
								TextFile textFile = new TextFile();

								if (textFileService.getTextFileByValue(key) != null) {							// if this word has already been in DB -> change his quantity
									textFile = textFileService.getTextFileByValue(key);
									int margin = value;
									int newCount = textFile.getCount() + margin;
									textFile.setCount(newCount);
									textFileService.editTextFile(textFile);

								} else {																		// if this word's new -> store into DB
									textFile.setValue(key);
									textFile.setCount(value);
									textFileService.addTextFile(textFile);
								}
							}
						}
						br.close();																				// close bufferedReader action

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						exec.shutdown();																		// close executorService action
					}
				}
			}
		});
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.OK);
	}






}
