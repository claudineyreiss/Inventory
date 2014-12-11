package com.siersolutions.converters;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.siersolutions.model.Product;
import com.siersolutions.repository.ProductRepository;

@Named
@ViewScoped
public class ProductConverter implements Converter, Serializable {

	@Inject
	private ProductRepository products;

	public ProductConverter() {
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Product r = null;
		if (value != null) {
			Long id = new Long(value);
			r = products.byId(id);
		}
		
		return r;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Product) value).getId().toString();
		}
		return "";
	}

}
