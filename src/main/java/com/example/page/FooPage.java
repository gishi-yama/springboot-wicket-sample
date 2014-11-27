package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.example.service.IBarService;

public class FooPage extends WebPage {
	
	@SpringBean
	private IBarService barService;

	public FooPage() {
		this.add(new Label("label", Model.of(barService.createMessage())));
	}

}
