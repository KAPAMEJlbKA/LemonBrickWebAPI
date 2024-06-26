package pro.gravit.simplecabinet.web.controller.cabinet;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pro.gravit.simplecabinet.web.dto.PageDto;
import pro.gravit.simplecabinet.web.dto.shop.PaymentDto;
import pro.gravit.simplecabinet.web.exception.InvalidParametersException;
import pro.gravit.simplecabinet.web.service.payment.*;
import pro.gravit.simplecabinet.web.service.shop.PaymentService;
import pro.gravit.simplecabinet.web.service.user.UserService;

@RestController
@RequestMapping("/cabinet/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private YooPaymentService yooPaymentService;
    @Autowired
    private FreekassaPaymentService freekassaPaymentService;
    @Autowired
    private TestPaymentService testPaymentService;
    @Autowired
    private StripePaymentService stripePaymentService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public PaymentInfoDto paymentCreate(@RequestBody BalancePaymentCreateRequest request, HttpServletRequest servletRequest) throws Exception {
        PaymentService.PaymentCreationInfo info;
        var user = userService.getCurrentUser();
        var ref = user.getReference();
        BasicPaymentService basicPaymentService = switch (request.system) {
            case "Yoo" -> yooPaymentService;
            case "Freekassa" -> freekassaPaymentService;
            case "Test" -> testPaymentService;
            case "Stripe" -> stripePaymentService;
            default -> throw new InvalidParametersException("Payment system not found", 11);
        };
        info = basicPaymentService.createBalancePayment(ref, request.sum, servletRequest.getRemoteAddr());
        return new PaymentInfoDto(info.redirect(), new PaymentDto(info.paymentInfo()));
    }

    @GetMapping("/page/{pageId}")
    public PageDto<PaymentDto> getPage(@PathVariable int pageId) {
        var user = userService.getCurrentUser();
        var list = paymentService.findAllByUser(user.getReference(), PageRequest.of(pageId, 10));
        return new PageDto<>(list.map(PaymentDto::new));
    }

    public record PaymentInfoDto(PaymentService.PaymentRedirectInfo redirect, PaymentDto payment) {

    }

    public record BalancePaymentCreateRequest(String system, double sum) {

    }
}
