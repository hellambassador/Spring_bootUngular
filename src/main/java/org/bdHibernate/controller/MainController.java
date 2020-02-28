package org.bdHibernate.controller;

import java.util.List;

import org.bdHibernate.bd.ManufacturerEntity;
import org.bdHibernate.bd.MaterialEntity;
import org.bdHibernate.dao.BankAccountDAO;
import org.bdHibernate.exception.BankTransactionException;
import org.bdHibernate.form.SendManufForm;
import org.bdHibernate.form.SendMoneyForm;
import org.bdHibernate.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private BankAccountDAO bankAccountDAO;

//    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {


        List<ManufacturerEntity> list =ManufacturerEntity.show();
        //List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();

        model.addAttribute("accountInfos", list);
        List<MaterialEntity> list1 =MaterialEntity.show();
        model.addAttribute("accountInfos1", list1);
        return "accountsPage";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {

        SendMoneyForm form = new SendMoneyForm(2L, 1L, 600d);
        SendManufForm form1 = new SendManufForm(1, "kirilicha", "dom");

        model.addAttribute("sendMoneyForm", form);
        model.addAttribute("SendManufForm", form1);

        return "sendMoneyPage";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {

        System.out.println("Send Money::" + sendMoneyForm.getAmount());

        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/addManuf", method = RequestMethod.POST)
    public String processaddManuf(Model model, SendManufForm sendManufForm) {

        System.out.println("Manuf Add::");
        ManufacturerEntity.add(sendManufForm.getname(),sendManufForm.getaddress());
        return "redirect:/";
    }

    @RequestMapping(value = "/updateManuf", method = RequestMethod.POST)
    public String processupdateManuf(Model model, SendManufForm sendManufForm) {

        System.out.println("Manuf update::");
        ManufacturerEntity.update(sendManufForm.getidManufacturer(),sendManufForm.getname(),sendManufForm.getaddress());
        return "redirect:/";
    }
    @RequestMapping(value = "/delManuf", method = RequestMethod.POST)
    public String processdelManuf(Model model, SendManufForm sendManufForm) {

        System.out.println("Manuf del::");
        ManufacturerEntity.delete(sendManufForm.getidManufacturer());
        return "redirect:/";
    }

}