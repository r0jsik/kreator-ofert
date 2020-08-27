package mr.output.text;


import java.util.List;

import mr.dialog.Dialog;
import mr.item.Item;
import mr.output.Output;


public class TextOutput implements Output
{
	private final Dialog dialog;
	private final TextOutputController controller;
	
	public TextOutput(Dialog dialog, TextOutputController controller)
	{
		this.dialog = dialog;
		this.controller = controller;
	}
	
	@Override
	public void show(List<Item> items)
	{
		String outputText = getOutputText(items);
		
		controller.show(outputText);
		dialog.show();
	}
	
	private String getOutputText(List<Item> items)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Item item : items)
		{
			stringBuilder.append(getOutputText(item));
			stringBuilder.append('\n');
		}
		
		return stringBuilder.toString();
	}
	
	private String getOutputText(Item item)
	{
		TextItemView view = new TextItemView();
		
		item.showOn(view);
		
		return view.toString();
	}
}