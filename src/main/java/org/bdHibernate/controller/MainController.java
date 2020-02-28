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
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/manufactures", method = RequestMethod.GET)
    public List<ManufacturerEntity>  showBankAccounts() {


        List<ManufacturerEntity> list =ManufacturerEntity.show();
        //List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();

//        model.addAttribute("accountInfos", list);
//        List<MaterialEntity> list1 =MaterialEntity.show();
//        model.addAttribute("accountInfos1", list1);
        return list;
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

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/manufacture/{id}", method = RequestMethod.DELETE)
    public void processdelManuf(@PathVariable String id) throws Exception {

        ManufacturerEntity.delete(Integer.parseInt(id));
    }

}