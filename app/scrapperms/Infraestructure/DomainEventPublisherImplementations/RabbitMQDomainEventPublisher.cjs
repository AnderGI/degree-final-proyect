const {
  DomainEventPublisher,
} = require("../../Domain/Ports/DomainEventPublisherInterface.cjs");
const amqp = require("amqplib");
const queue = "car_scrapping";
const RabbitMQDomainEventPublisher = (data) => {
  const proto = {
    publishEvent: async () => {
      let connection;
      try {
        connection = await amqp.connect("amqp://localhost");
        const channel = await connection.createChannel();
        await channel.assertQueue(queue, { durable: false });
        channel.sendToQueue(queue, Buffer.from(JSON.stringify(data)));
        await channel.close();
      } catch (err) {
        console.warn(err);
      } finally {
        if (connection) await connection.close();
      }
    },
  };

  const composite = DomainEventPublisher(proto);

  return Object.assign(Object.create(composite), proto);
};

module.exports = { RabbitMQDomainEventPublisher };
