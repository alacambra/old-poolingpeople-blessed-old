package poolingpeople.adminbackend.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent ui, String value) {
		try {
			return new SimpleDateFormat("dd.MM.yyyy").parse(value).getTime();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Invalid date provided",""));
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent ui, Object value) {
		Long dateAsTimeStamp = (Long) value;
		Date date = new Date(dateAsTimeStamp);
		return new SimpleDateFormat("dd.MM.yyyy").format(date);
	}

}
