package gft.cryptoWallet.exception;

import gft.cryptoWallet.dto.ApiErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CryptoWalletException.class })
    public ResponseEntity<ApiErrorDTO> handleCriptoWalletException(CryptoWalletException ex, WebRequest request) {

        String error = "Erro no sistema";

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

        String error = "Recurso não encontrado";

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
    }

    @ExceptionHandler({ DataInsertionException.class })
    public ResponseEntity<ApiErrorDTO> DataInsertionException(DataInsertionException ex, WebRequest request) {

        String error = "Não foi possivel inserir esses dados na base de dados.";

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
    }
}
