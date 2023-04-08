package pl.kosmala.shop.common.mail;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@Getter
public class EmailConfig
{
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.orderConfirmation}")
    private String orderConfirmationQueue;
    @Value("${rabbitmq.queue.accountConfirmation}")
    private String accountConfirmationQueue;

    @Value("${rabbitmq.routing-keys.internal-accountConfirmation}")
    private String internalAccountConfirmationRoutingKey;
    @Value("${rabbitmq.routing-keys.internal-orderConfirmation}")
    private String internalOrderConfirmationRoutingKey;
    @Bean
    public TopicExchange internalTopicExchange()
    {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue accountConfirmationQueue()
    {
        return new Queue(this.accountConfirmationQueue);
    }

    @Bean
    public Queue orderConfirmationQueue()
    {
        return new Queue(this.orderConfirmationQueue);
    }

    @Bean
    public Binding internalOrderConfirmationBinding()
    {
        return BindingBuilder.bind(orderConfirmationQueue()).to(internalTopicExchange()).with(this.internalOrderConfirmationRoutingKey);
    }
    @Bean
    public Binding internalAccountConfirmationBinding()
    {
        return BindingBuilder.bind(accountConfirmationQueue()).to(internalTopicExchange()).with(this.internalAccountConfirmationRoutingKey);
    }
}
