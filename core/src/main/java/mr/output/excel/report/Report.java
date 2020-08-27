package mr.output.excel.report;


import java.time.LocalDate;
import java.util.List;

import mr.item.Item;
import mr.output.excel.Agent;


public interface Report
{
	void showCreationDate(LocalDate value);
	void showPaymentType(String value);
	void showDeliveryType(String value);
	void showDeliveryTime(String value);
	void showExpirationDate(LocalDate value);
	void showReceiver(String value);
	void showFirmLabel(String value);
	void showAddress(String value);
	void showPhone(String value);
	void showFAX(String value);
	void showEmail(String value);
	void showOfferType(String value);
	void showIdentifier(String value);
	void showAgents(Agent tradesman, Agent assistant);
	void showItems(List<Item> items);
	void showTotalPrice();
	void showFooter();
}