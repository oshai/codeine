package codeine;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class CodeineUncaughtExceptionHandler implements UncaughtExceptionHandler {

	private Logger log = Logger.getLogger(CodeineUncaughtExceptionHandler.class);

	private boolean errorPrintedToOut = false;

	public CodeineUncaughtExceptionHandler() {
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		try {
			System.out.println("Uncaught exception!");
			if (!errorPrintedToOut) {
				errorPrintedToOut = true;
				e.printStackTrace();
			}
			log.error("Uncaught exception in thread " + t.getName(), e);
		} catch (Throwable tr) {
			try {
				e.printStackTrace();
				tr.printStackTrace();
			} catch (Throwable e1) {
				log.fatal("could not write stacktrace of exception " + t.getName());
				addExceptionInfo(e1);
				addExceptionInfo(e);
				addExceptionInfo(tr);
			}
		}
	}

	private void addExceptionInfo(Throwable e) {
		log.fatal("exception info " + e.getMessage() + " " + e);
	}

	public static void main(String[] args) {
		
	}
	public static void main1(String[] args) {
		BasicConfigurator.configure();
		Thread.setDefaultUncaughtExceptionHandler(new CodeineUncaughtExceptionHandler());
		throw new RuntimeException() {
			private static final long serialVersionUID = 1L;

			@Override
			public void printStackTrace(java.io.PrintWriter s) {
				overflow();

			}

			@Override
			public void printStackTrace() {
				overflow();
			}

			private void overflow() {
				overflow();
			}

		};
	}

}