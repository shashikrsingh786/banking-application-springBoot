package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.models.Account;
import com.banking.models.TransactionSummary;
import com.banking.repositories.AccountRepository;
import com.banking.services.AccountService;
import com.banking.services.TransactionService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("mybank/user/transaction")
public class TransactionController {

	
	
	@Autowired private AccountService accountService;
	@Autowired private TransactionService transactionService;
	@Autowired private AccountRepository accountResp;
	
	@RequestMapping("view-balance")
	public String showBalance(Model model,HttpSession session) {
		int amount = accountService.getAmount((Integer)session.getAttribute("accountno"));
		model.addAttribute("amount",amount);
		return "transaction/show-balance";
	}
	
	@RequestMapping("deposit-money")
	public String getDepositMoneyForm() {
		return "transaction/deposit-money";
	}
	
	@RequestMapping("update-deposit-amount")
	public String DepositMoney(int amount,HttpSession ses, Model model) {
		int an=(Integer)ses.getAttribute("accountno");
		transactionService.depositAmount(amount,an);
		model.addAttribute("amount",amount);
		return "transaction/deposit-success";
	}
	@RequestMapping("withdraw-money")
	public String getWithDrawMoneyView() {
		return "transaction/withdraw-money";
	}
	@RequestMapping("update-withdraw-amount")
	public String WithDrawMoney(int amount,HttpSession ses, Model model) {
		int an=(Integer)ses.getAttribute("accountno");
		if(amount>accountService.getAmount(an))
		{
			model.addAttribute("msg","Sorruy!!! You do not have sufficient amount");
			model.addAttribute("amt",amount);
			return "transaction/withdraw-money";
		}
		transactionService.withdrawAmount(amount,an);
		model.addAttribute("amount",amount);
		return "transaction/withdraw-success";
	}
	@RequestMapping("transaction-summary")
	public String transactionSummary(Model model,HttpSession ses) {
		int an=(Integer)ses.getAttribute("accountno");
		List<TransactionSummary> tlist = transactionService.getList(an);
		model.addAttribute("tlist", tlist);
		return "transaction/summary";
	}
	@RequestMapping("beneficiary-accountno")
	public String getTransferMoneyView() {
		return "transaction/transfer-money";
	}
	
	@RequestMapping("transfer-money")
	public String transferMoney(HttpSession ses, int wamount, Integer accountNo,Model model) {
		int an=(Integer)ses.getAttribute("accountno");
		if(wamount>accountService.getAmount(an))
		{
			model.addAttribute("msg","Sorry!!! You do not have sufficient amount");
			return "transaction/transfer-money";
		}
		String exist = accountResp.isAccountExist(accountNo);
		
		if(exist == null) {
			model.addAttribute("msg","Entered account number do not exist");
			return "transaction/transfer-money";
		}
		if(accountNo == an) {
			model.addAttribute("msg","You can't send money to yourself");
			return "transaction/transfer-money";
		}
		transactionService.withdrawAmount(wamount,an);
		transactionService.depositAmount(wamount, accountNo);		
		model.addAttribute("amount", wamount);
		return "transaction/transfer-money-success";
		
	}
	
}
