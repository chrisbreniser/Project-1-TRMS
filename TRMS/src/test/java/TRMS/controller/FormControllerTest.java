package TRMS.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import TRMS.model.Form;
import TRMS.service.FormService;
import io.javalin.http.Context;

public class FormControllerTest {
	
	@Mock
	private FormService mockFormService;
	
	@Mock
	private Context mockCtx;
	
	private FormController formController;
	private Form form;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		formController = new FormController(mockFormService);
		form = new Form(
				100,
				"certification_preparation_class",
				"2021-01-01",
				"13:00:00",
				"Washington",
				"A class to teach about a new production tool.",
				250,
				null,
				"This seminar will help me understand advanced product production using this great new tool!",
				"certification_test",
				null,
				2);
		
		when(mockCtx.formParam("event_type")).thenReturn(form.getEventType());
		when(mockCtx.formParam("event_time")).thenReturn(form.getEventTime());
		when(mockCtx.formParam("event_location")).thenReturn(form.getEventLocation());
		when(mockCtx.formParam("event_description")).thenReturn(form.getEventDescription());
		when(mockCtx.formParam("grading_format")).thenReturn(form.getGradingFormat());
		when(mockCtx.formParam("event_cost")).thenReturn(Double.toString(form.getEventCost()));
		when(mockCtx.formParam("justification")).thenReturn(form.getJustification());
		when(mockCtx.formParam("hours_missed", "0")).thenReturn(Double.toString(form.getHoursMissed()));	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateForm() {
		try {
			formController.createForm(mockCtx);
		
			verify(mockCtx).formParam("event_type");
			verify(mockCtx).formParam("event_time");
			verify(mockCtx).formParam("event_location");
			verify(mockCtx).formParam("event_description");
			verify(mockCtx).formParam("grading_format");
			verify(mockCtx).formParam("event_cost");
			verify(mockCtx).formParam("justification");
			verify(mockCtx).formParam("hours_missed", "0");
			
			verify(mockFormService).createFrom(form);
			
			verify(mockCtx).status(201);
			                                             
			
		} catch (Exception e) {
			fail("Failed during create form test by thrown exception: " + e);
		}
	}

}
