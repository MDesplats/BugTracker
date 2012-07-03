package com.odea;


import com.odea.components.ajax.AjaxCSVDownloadLink;
import com.odea.components.jquery.datatable.JQueryBasicDataTable;
import com.odea.dao.TicketDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * User: pbergonzi
 * Date: 18/06/12
 * Time: 18:21
 */
public class Grid extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(Grid.class);
    private transient TicketDAO ticketDAO = TicketDAO.getInstance();

    public Grid(){
        logger.debug("Loading big grid");
        AjaxCSVDownloadLink boton = new AjaxCSVDownloadLink("boton") {
            @Override
            protected String getFileName() {
                return "pepe.csv";
            }

            @Override
            protected String getCsvData() {
                return "pepe,loco,101,2020";
            }
        };
        this.add(boton);
        JQueryBasicDataTable tabla = new JQueryBasicDataTable("tabla") {
            @Override
            public Collection getSearchResults(String searchToken) {
                return ticketDAO.getTickets();
            }

            @Override
            public String[] getColumns() {
                return new String[]{"id", "title" , "description", "type", "status"};
            }
        };

        add(tabla);
    }

}
