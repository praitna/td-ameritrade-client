package com.studerw.tda.client;

import com.studerw.tda.model.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TdaXmlParser {

    private HttpTdaClient client;

    public TdaXmlParser(HttpTdaClient client) {
        this.client = client;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TdaXmlParser.class);

//    protected <T> parseXml(T type, String xml){
//        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
//            LOGGER.debug("unmarshalling xml to pojo of type: {}", type.getClass().getName());
//            JAXBContext context = JAXBContext.newInstance(type.getClass());
//            Unmarshaller um = context.createUnmarshaller();
//            final T obj = (T)um.unmarshal(in);
//            login.setOriginalXml(xml);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IllegalStateException("Error parsing login");
//        }
//    }

    protected Login parseLoginXml(String xml){
        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
            JAXBContext context = JAXBContext.newInstance(Login.class);
            Unmarshaller um = context.createUnmarshaller();
            Login login = (Login) um.unmarshal(in);
            login.setOriginalXml(xml);
            this.client.currentLogin = login;
            if (login.getResult().equalsIgnoreCase("FAIL")) {
                login.setTdaError(true);
            }
            return login;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error parsing login");
        }
    }

    protected QuoteResponse parseQuoteResponse(String xml){
        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
            JAXBContext context = JAXBContext.newInstance(QuoteResponse.class);
            Unmarshaller um = context.createUnmarshaller();
            QuoteResponse quoteResponse = (QuoteResponse) um.unmarshal(in);
            quoteResponse.setOriginalXml(xml);
            return quoteResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error parsing Quote Response");
        }
    }

    protected OrderStatus parseOrderStatus(String xml){
        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
            JAXBContext context = JAXBContext.newInstance(OrderStatus.class);
            Unmarshaller um = context.createUnmarshaller();
            OrderStatus orderStatus = (OrderStatus) um.unmarshal(in);
            orderStatus.setOriginalXml(xml);
            return orderStatus;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error parsing Order Status");
        }

    }

    protected BalancesAndPositions parseBalances(String xml){
        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
            JAXBContext context = JAXBContext.newInstance(BalancesAndPositions.class);
            Unmarshaller um = context.createUnmarshaller();
            BalancesAndPositions balances = (BalancesAndPositions) um.unmarshal(in);
            balances.setOriginalXml(xml);
            return balances;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error parsing balances");
        }
    }

    protected OptionTradeResponse parseOptionTradeResponse(String xml) {
        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
            JAXBContext context = JAXBContext.newInstance(OptionTradeResponse.class);
            Unmarshaller um = context.createUnmarshaller();
            OptionTradeResponse optionTradeResponse = (OptionTradeResponse) um.unmarshal(in);
            optionTradeResponse.setOriginalXml(xml);
            return optionTradeResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error parsing option trade response");
        }
    }

    protected OptionChain parseOptionChain(String xml) {
        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
            JAXBContext context = JAXBContext.newInstance(OptionChain.class);
            Unmarshaller um = context.createUnmarshaller();
            OptionChain optionChain = (OptionChain) um.unmarshal(in);
            optionChain.setOriginalXml(xml);
            return optionChain;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error parsing optionChain");
        }
    }

}