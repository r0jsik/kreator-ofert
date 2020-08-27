package mr.main;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import mr.command.ClientsCommandInterpreter;
import mr.command.PriceListsCommandInterpreter;
import mr.command.UnknownCommandException;
import mr.help.*;


public class CommandLineTools
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		showIntro();
		
		CommandLineTools commandLineTools = new CommandLineTools();
		commandLineTools.commandProcessing(scanner);
	}
	
	private static void showIntro()
	{
		HelpProvider helpProvider = new FileHelpProvider("/help.txt");
		HelpOutput helpOutput = new PrintingHelpOutput();
		
		helpOutput.show(helpProvider);
	}
	
	private void commandProcessing(Scanner scanner)
	{
		do
		{
			System.out.print("> ");
			
			try
			{
				interpretCommand(scanner);
			}
			catch (UnknownCommandException exception)
			{
				System.out.println("Nieznana komenda");
			}
			catch (ArrayIndexOutOfBoundsException exception)
			{
				System.out.println("Nie podano nazwy akcji");
			}
			catch (InvocationTargetException exception)
			{
				System.out.println("Błąd narzędzia: " + exception.getTargetException().getMessage());
			}
			catch (ReflectiveOperationException exception)
			{
				System.out.println("Nieznana akcja lub nieprawidłowa liczba argumentów");
			}
			catch (ShutdownRequest shutdownRequest)
			{
				break;
			}
		}
		while (true);
	}
	
	private void interpretCommand(Scanner scanner) throws ReflectiveOperationException
	{
		String input = scanner.nextLine();
		String[] command = input.split(" ");
		
		Class<?> interpreter = getInterpreter(command[0]);
		
		invoke(interpreter, command,command.length - 2);
	}
	
	private Class<?> getInterpreter(String command)
	{
		switch (command)
		{
			case "clients":
				return ClientsCommandInterpreter.class;
			
			case "price-lists":
				return PriceListsCommandInterpreter.class;
			
			case "exit":
				throw new ShutdownRequest();
		}
		
		throw new UnknownCommandException(command);
	}
	
	private void invoke(Class<?> interpreterClass, String[] command, int argumentCount) throws ReflectiveOperationException
	{
		Method method = getMethod(interpreterClass, command[1], argumentCount);
		Object interpreterInstance = interpreterClass.newInstance();
		String[] arguments = Arrays.copyOfRange(command, 2, argumentCount + 2);
		
		method.invoke(interpreterInstance, (Object[]) arguments);
	}
	
	private Method getMethod(Class<?> interpreterClass, String actionName, int argumentCount) throws NoSuchMethodException
	{
		Class<String>[] argumentTypes = new Class[argumentCount];
		
		Arrays.fill(argumentTypes, String.class);
		
		Method method = interpreterClass.getDeclaredMethod(actionName, argumentTypes);
		method.setAccessible(true);
		
		return method;
	}
}