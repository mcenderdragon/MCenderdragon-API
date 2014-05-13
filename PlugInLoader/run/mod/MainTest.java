package mod;

import java.util.Date;

import dragon.plugin.Init.Instance;
import dragon.plugin.Init.Plugin;
import dragon.plugin.Init.Start;

@Plugin(pluginID = "TestPlugin")
public class MainTest 
{
	@Instance
	public MainTest instance;
	
	@Instance("SYSTEM")
	public Object instance2;
	
	@Start
	public void main()
	{
		System.out.println("Start "+new Date());
		System.out.println(instance);
		System.out.println(instance2);
	}
	
}