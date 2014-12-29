package com.example.page;
import static org.mockito.Mockito.*;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.WebInitializer;
import com.example.WicketApplication;
import com.example.service.IBarService;

@RunWith(Enclosed.class)
public class FooPageTest {
		
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = { WebInitializer.class, WicketApplication.class })
	public static class モックありテスト {

		@Autowired
		private WicketApplication wicketApplication;
		private ApplicationContextMock applicationContextMock;
		private WicketTester sut;

		@Before
		public void setUp() throws Exception {
			sut = new WicketTester(wicketApplication);
			applicationContextMock = new ApplicationContextMock();
			sut.getApplication().getComponentInstantiationListeners()
					.add(new SpringComponentInjector(sut.getApplication(), applicationContextMock));
			IBarService mock = mock(IBarService.class);
			when(mock.createMessage()).thenReturn("it is mocked!");
			applicationContextMock.putBean("barService", mock);
		}

		@Test
		@DirtiesContext 
		public void FooPageが表示される() {
			sut.startPage(FooPage.class);
			sut.assertRenderedPage(FooPage.class);
			sut.assertLabel("label", "it is mocked!");
		}
		
	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = { WebInitializer.class, WicketApplication.class })
	public static class モックなしテスト {

		@Autowired
		private WicketApplication wicketApplication;
		private WicketTester sut;

		@Before
		public void setUp() throws Exception {
			sut = new WicketTester(wicketApplication);
		}

		@Test
		@DirtiesContext 
		public void FooPageが表示される() {
			sut.startPage(FooPage.class);
			sut.assertRenderedPage(FooPage.class);
			sut.assertLabel("label", "Hello, Spring Boot + Wicket!");
		}
	}

}
