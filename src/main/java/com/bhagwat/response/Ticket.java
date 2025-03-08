package com.bhagwat.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class Ticket {

    private int ticketId;
    private String from;
    private String to;
    private String trainNum;
    private String tktCost;
    private String ticketStatus;
}
