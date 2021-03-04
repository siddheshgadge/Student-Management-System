class App {
	public static void main(String args[]) {
		CreateSplashScreen splash = new CreateSplashScreen();
      try {
         Thread.sleep(1500);
         splash.dispose();
      } catch(Exception e) {
         e.printStackTrace();
      }
		MainFrame m = new MainFrame();
	}
}