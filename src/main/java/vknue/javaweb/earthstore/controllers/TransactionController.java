package vknue.javaweb.earthstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vknue.javaweb.earthstore.models.Transaction;
import vknue.javaweb.earthstore.repositories.ITransactionRepository;
import vknue.javaweb.earthstore.repositories.IUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private IUserRepository userRepository;


    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        long id = userRepository.findByUsername(username).getId();
        List<Transaction> transactions = transactionRepository
                .findAll()
                .stream()
                .filter(transaction -> transaction.getUser().getId() == id)
                .collect(Collectors.toList());
        model.addAttribute("transactions", transactions);

        return "transactions";
    }



    @GetMapping("/admin/index")
    public String adminIndex(@RequestParam(value ="", required = false) String username,
                             @RequestParam(value = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             Model model) {

        List<Transaction> transactions = transactionRepository.findAll();
        if (username != null && !username.isEmpty()) {
            final String finalUsername = username;
            transactions = transactions.stream()
                    .filter(transaction -> transaction.getUser().getUsername().contains(finalUsername))
                    .collect(Collectors.toList());
        }
        if (date != null) {
            transactions = transactions.stream()
                    .filter(transaction -> transaction.getPurchaseDate().after(date))
                    .collect(Collectors.toList());
        }
        model.addAttribute("transactions", transactions);

        return "transactionsAdmin";
    }
    @PostMapping("/details")
    public String getDrinks(@RequestParam("transactionId") long transactionId,
                            Model model) {
        Transaction transaction = transactionRepository.getReferenceById(transactionId);
        model.addAttribute("transaction", transaction);

        return "transactionDetails";
    }
}