
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.zigi.tool.jpassrem.breaker.PdfBreaker;
import org.zigi.tool.jpassrem.generator.StringGenerator;

public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		PdfBreaker breaker = null;
		try {
			long startTime = System.nanoTime();

			breaker = PdfBreaker.getInstance("locked.pdf");
			char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e' };
			StringGenerator pass = StringGenerator.getInstance(chars, 3, 3);

			while (pass.hasNext()) {
				String password = pass.next();
				System.out.println(password);
				if (breaker.breakPass(password)) {
					long estimatedTime = System.nanoTime() - startTime;
					estimatedTime -= TimeUnit.HOURS.toNanos(TimeUnit.NANOSECONDS.toHours(estimatedTime));
					long minutes = TimeUnit.NANOSECONDS.toMinutes(estimatedTime);
					estimatedTime -= TimeUnit.MINUTES.toNanos(minutes);
					long seconds = TimeUnit.NANOSECONDS.toSeconds(estimatedTime);
					String elapsedText = String.format("%d minut a %d sekund", minutes, seconds);
					LOG.info("Heslo: " + password + " nalezeno za " + elapsedText);
					break;
				}
			}
		} catch (Exception e) {
			LOG.error("Heslo k PDF souboru se nepodaøilo prolomit", e);
		}
	}

}
