package mr.output.excel;


import java.util.function.BiConsumer;

import mr.settings.Format;


public class Agent
{
	private final String position;
	private final String name;
	private final String phone;
	private final String email;
	
	public Agent(String position, String name, String phone, String email)
	{
		this.position = position;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public void withPersonalData(BiConsumer<String, String> personalDataConsumer)
	{
		personalDataConsumer.accept(position, Format.asAgent(name, phone, email));
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}