package mr.price.list.loader;


import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import mr.settings.preferences.Preferences;


public class PriceListLoaderCacheInitializer implements BeanPostProcessor, ApplicationContextAware
{
	private boolean isCacheOn;
	
	@Override
	public Object postProcessBeforeInitialization(Object object, String beanName)
	{
		if (isCacheOn && (object instanceof PriceListLoader))
		{
			isCacheOn = false;
			
			return new CachedPriceListLoader((PriceListLoader) object);
		}
		
		return object;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object object, String beanName)
	{
		return object;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context)
	{
		Preferences preferences = (Preferences) context.getBean("mainPreferences");
		
		if (preferences.getBoolean("price-list", "cache"))
		{
			isCacheOn = true;
		}
	}
}