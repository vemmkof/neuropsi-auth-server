package mx.ipn.escom.neuropsiauthserver.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
  private static final long serialVersionUID = 1L;
  String message;
}
