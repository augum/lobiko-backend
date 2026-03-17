package lobiko.com.services;

import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import lobiko.com.entities.StripeObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentIntent createPaymentIntent(StripeObject payement) throws Exception {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) payement.getAmount()) // Montant en centimes
                .setCurrency("usd")
                .setPaymentMethod(payement.getPaymentMethodId())
                .setConfirm(true)
                .setDescription("Paiement sur lobiko")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER)
                                .build()
                )
                .build();

        return PaymentIntent.create(params);
    }
}
