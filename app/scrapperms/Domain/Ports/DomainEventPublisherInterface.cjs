const DomainEventPublisher = (interfaceImpl) => ({
  type: "DomainEventPublisher",
  publishEvent: () => interfaceImpl.publishEvent(),
});

module.exports = { DomainEventPublisher };
