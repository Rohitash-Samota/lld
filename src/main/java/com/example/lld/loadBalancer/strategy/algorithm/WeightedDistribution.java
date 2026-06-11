import java.util.concurrent.ThreadLocalRandom;

public class WeightedDistribution extends LoadBalancer {

    private final Map<RequestType, Integer> totalWeightMap =  new HashMap<>();

    public WeightedDistribution(Map<RequestType, List<Server>> serverMap) {
        super(serverMap);
        for (Map.Entry<RequestType, List<Server>> entry : serverMap.entrySet()) {
            int totalWeight = entry.getValue().stream().mapToInt(Server::getWeight).sum();
            totalWeightMap.put(entry.getKey(), totalWeight);
        }
    }

    @Override
    public Destination getDestination(RequestType requestType) {

        List<Server> servers = getServers(requestType);

        if (servers.isEmpty()) {
            return null;
        }

        int totalWeight = totalWeightMap.get(requestType);
        int random = ThreadLocalRandom.current().nextInt(totalWeight);
        int cumulative = 0;

        for (Server server : servers) {
            cumulative += server.getWeight();
            if (random < cumulative) {
                return server.getDestination();
            }
        }

        return servers.get(servers.size() - 1).getDestination();
    }
}