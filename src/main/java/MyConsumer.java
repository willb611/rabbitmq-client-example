import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {
  private static final Logger logger = LoggerFactory.getLogger(MyConsumer.class);

  /**
   * Constructs a new instance and records its association to the passed-in channel.
   *
   * @param channel the channel to which this consumer is attached
   */
  public MyConsumer(Channel channel) {
    super(channel);
  }

  @Override
  public void handleDelivery(String consumerTag, Envelope envelope,
                             AMQP.BasicProperties properties, byte[] body) throws IOException {
    try {
      String message = new String(body, "UTF-8");
      logger.info("[handleDelivery] Received message: " + message);

    } catch (Exception e) {
      throw e;
    } finally {
      getChannel().basicAck(envelope.getDeliveryTag(), false);
    }
  }
}
