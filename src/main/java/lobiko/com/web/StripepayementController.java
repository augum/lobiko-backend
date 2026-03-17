package lobiko.com.web;

import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import lobiko.com.dtos.PaymentResponseDto;
import lobiko.com.entities.StripeObject;
import lobiko.com.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class StripepayementController {

    private PaymentService paymentService;

    @PostMapping("/charge")
    public ResponseEntity<?> charge(@RequestBody StripeObject request) {
        try {
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(request);
            PaymentResponseDto response = new PaymentResponseDto(paymentIntent.getId(),paymentIntent.getClientSecret());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Charge failed: " + e.getMessage());
        }
    }
}

