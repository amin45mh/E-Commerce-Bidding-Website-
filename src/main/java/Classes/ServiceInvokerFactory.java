package Classes;

//import beans.ServiceData;

public class ServiceInvokerFactory {
	public IServiceInvoker createService(String checked) {
		IServiceInvoker result = null;
	
		if (checked.equals("S1")) 
			result = new Service1Invoker();
		else if (checked.equals("S2"))
			result = new Service2Invoker();
		else if (checked.equals("S3"))
			result = new Service3Invoker();
		return result;

	}
}
