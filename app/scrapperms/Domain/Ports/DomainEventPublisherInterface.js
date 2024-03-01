export const DomainEventPublisher = (interfaceImpl) => ({
  type: "DomainEventPublisher",
  publishEvent: () => interfaceImpl.publishEvent(),
});
