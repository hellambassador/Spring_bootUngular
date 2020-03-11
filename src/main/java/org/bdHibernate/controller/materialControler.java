package org.bdHibernate.controller;

        import java.util.List;

        import org.bdHibernate.bd.ManufacturerEntity;
        import org.bdHibernate.bd.MaterialEntity;
        import org.bdHibernate.bd.ToolEntity;
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
public class materialControler {

    @Autowired
    private BankAccountDAO bankAccountDAO;


    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/materials", method = RequestMethod.GET)
    public List<MaterialEntity>  showMaterial() {
        List<MaterialEntity> list =MaterialEntity.show();
        return list;
    }
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/tools", method = RequestMethod.GET)
    public List<ToolEntity>  showTools() throws Exception {
        List<ToolEntity> list =ToolEntity.show();
        return list;
    }
    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/material/{id}", method = RequestMethod.DELETE)
    public void processDelMat(@PathVariable String id) throws Exception {
        MaterialEntity.delete(Integer.parseInt(id));
    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/material/{id}", method = RequestMethod.PUT)
    public void processUpdateMat(@PathVariable String id,@RequestBody  MaterialEntity manuf) throws Exception {
        manuf.update();
    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/api/material", method = RequestMethod.POST)
     public MaterialEntity processAddMat(@RequestBody  MaterialEntity manuf) throws Exception {
            return manuf.add();
    }


}