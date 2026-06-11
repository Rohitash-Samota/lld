package com.example.lld.loadBalancer.registry;

public class ServerRegistry {

    private static ServerRegistry instance;
    private final CopyOnWriteArrayList<Server> servers = new CopyOnWriteArrayList<>();


    public void register(Server server) {
        servers.add(server);
    }

    public static ServerRegistry getInstance() {
        if(instance == null) {
            instance = new ServerRegistry();
        }
        return instance;
    }

    public void deregister(String serverId) {

        servers.removeIf(server -> server.getId().equals(serverId));
    }

    public List<Server> getHealthyServers() {
        return servers.stream().filter(Server::isHealthy).toList();
    }
}