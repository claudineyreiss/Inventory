package com.siersolutions.converters;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.siersolutions.model.Category;
import com.siersolutions.repository.CategoryRepository;

@Named
@RequestScoped
public class CategoryConverter implements Converter, Serializable {

	@Inject
	private CategoryRepository categories;

	public CategoryConverter() {
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Category r = null;

		if (value != null) {
			Long id = new Long(value);
			r = categories.byId(id);
			System.out.println("chamou do getasobject");
		}
		return r;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			System.out.println("chamou do getasstring");

			return ((Category) value).getId().toString();
		}
		return "";
	}

}
