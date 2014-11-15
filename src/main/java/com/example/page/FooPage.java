package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class FooPage extends WebPage {

	public FooPage() {

		this.add(new Label("label", "Hello, World."));

	}

}
