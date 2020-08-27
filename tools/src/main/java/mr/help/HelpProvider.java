package mr.help;


import java.util.function.Consumer;


public interface HelpProvider
{
	void provide(Consumer<String> helpSentenceConsumer);
}