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

//    @ResponseBody
//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/api/materials", method = RequestMethod.GET)
//    public List<MaterialEntity>  showMaterial() {
//        List<MaterialEntity> list =MaterialEntity.show();
//        return list;
//    }
    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/manufacture/{id}", method = RequestMethod.DELETE)
    public void processDelManuf(@PathVariable String id) throws Exception {
        ManufacturerEntity.delete(Integer.parseInt(id));
    }
    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/manufacture/name", method = RequestMethod.POST)
    public ManufacturerEntity manufByName(@RequestBody  String name) throws Exception {
        return ManufacturerEntity.byName(name);
    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/manufacture/{id}", method = RequestMethod.PUT)
    public void processUpdateManuf(@PathVariable String id,@RequestBody  ManufacturerEntity manuf) throws Exception {
        ManufacturerEntity.update(manuf.getIdManufacturer(),manuf.getName(),manuf.getAddress());
    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/manufacture", method = RequestMethod.POST)
    public ManufacturerEntity processAddManuf(@RequestBody  ManufacturerEntity manuf) throws Exception {
        return ManufacturerEntity.add(manuf.getName(),manuf.getAddress());
    }

}