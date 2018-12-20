package com.qa.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

import com.qa.testConstants.Constants;

import junit.framework.Assert;

import com.qa.persistence.domain.CV;
import com.qa.persistence.domain.Trainee;
import com.qa.rest.EndpointInterface;
import com.qa.service.CVService;

@RunWith(MockitoJUnitRunner.class)
public class CVEndpointsTests {
	
	@InjectMocks
	private EndpointInterface endpoints;
	
	@Mock
	CVService service;
	
	@Test
	public void testmultiUploadFileModel() {
		MockMultipartFile mockMultipartFile = new MockMultipartFile(Constants.MOCK_TRAINEE_FILE,Constants.MOCK_FILENAME,
	              Constants.FILE_TYPE, Constants.MOCK_TEST_DATA.getBytes());
		Trainee trainee = new Trainee();
		trainee.setID(1L);
		Mockito.when(service.uploadFile(mockMultipartFile, 1L)).thenReturn(Constants.SERVICE_RESPONSE_ENTITY_MESSAGE);
		Assert.assertEquals(Constants.SERVICE_RESPONSE_ENTITY_MESSAGE, endpoints.multiUploadFileModel(mockMultipartFile, trainee.getID()));
	}
	
	@Test
	public void testCreateTrainee() {
		Trainee trainee = new Trainee();
		Mockito.when(service.createTrainee(trainee)).thenReturn(trainee);
		Assert.assertEquals(trainee, endpoints.createTrainee(trainee));
		
	}
	
	@Test
	public void testGetCV() {
		CV cv = new CV();
		Trainee trainee = new Trainee();
		trainee.setID(1L);
		List<Optional<CV>> cvList = new ArrayList<Optional<CV>>();
		cvList.add(Optional.of(cv));
		Mockito.when(service.getCV(trainee.getID())).thenReturn(cvList);
		Assert.assertEquals(cvList, endpoints.getCV(trainee.getID()));
	}

}
