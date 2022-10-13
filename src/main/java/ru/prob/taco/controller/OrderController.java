package ru.prob.taco.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.prob.taco.data.OrderRepository;
import ru.prob.taco.model.TacoOrder;
import ru.prob.taco.model.UserU;
import ru.prob.taco.service.OrderMessagingService;
import ru.prob.taco.web.OrderProps;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepo;
    private OrderMessagingService messagingService;
    private OrderProps props;

    public OrderController(OrderRepository orderRepo, OrderMessagingService messagingService, OrderProps props) {
        this.orderRepo = orderRepo;
        this.messagingService = messagingService;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal UserU user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUserU(user);
        messagingService.sendOrder(orderRepo.save(order));
        log.info("OOOO---> Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal UserU user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserUOrderByPlaceAtDesc(user, pageable));
    return "orderList";
    }

}
