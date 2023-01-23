package provisio.ui;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "provisio.ui.Button", createTag = true)
public class Button extends UIComponentBase {
 
 @Override
 public String getFamily() {
  return "provisio.ui.Button";
 }
 
 @Override
 public void encodeBegin(FacesContext context) throws IOException {
  ResponseWriter writer = context.getResponseWriter();
  String text = (String) getAttributes().get("text");
  writer.write("<button class='provisioButton'>");
  writer.write(text);
  writer.write("</button>");
 }
}