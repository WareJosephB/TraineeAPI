package com.qa.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qa.persistence.domain.CV;
import com.qa.service.ICVService;
import com.qa.util.Constants;

@RequestMapping("${URL.base}")
@CrossOrigin
@RestController
public class CVEndpoints implements EndpointInterface<CV> {

	@Autowired
	private ICVService cvService;

	@GetMapping("${URL.method.CV.getAll}")
	public Iterable<CV> getAll() {
		return cvService.getAllCVs();
	}
	
	@GetMapping("${URL.method.CV.getForUser}/{username}")
	public String getAll(@PathVariable("username") String username) {
		return cvService.getAllCVs(username);
	}


	@Override
	@DeleteMapping("${URL.method.CV.delete}/{cvID}")
	public String delete(@PathVariable("cvID") String cvid) {
		return cvService.deleteCV(Long.valueOf(cvid));
	}

	@PostMapping("${URL.method.CV.upload}/{username}")
	public String create(@RequestParam("cvDoc") MultipartFile cvDoc, @PathVariable("username") String traineeUsername) {
		return cvService.uploadFile(cvDoc, traineeUsername);
	}

	@Override
	public String update(@PathVariable("cvID") String username, @RequestBody CV updatedCv) {
		return Constants.MALFORMED_REQUEST_MESSAGE;
	}

	@GetMapping("${URL.method.CV.getByID}/{cvID}")
	public ResponseEntity<ByteArrayResource> get(@PathVariable("cvID") Long cvID) {
		return cvService.downloadFile(cvID);
	}

	

}
