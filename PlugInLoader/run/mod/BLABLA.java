package mod;

import java.util.Date;

import dragon.plugin.Init.Instance;
import dragon.plugin.Init.Plugin;
import dragon.plugin.Init.Start;

@Plugin(pluginID = "BLABLA")
public class BLABLA 
{
	@Instance("BLABLA")
	public BLABLA instance1;
	
	@Instance("TestPlugin")
	public MainTest instance2;
	
	@Start
	public void main()
	{
		System.err.println(instance1);
		System.err.println(instance2);
	}
}
