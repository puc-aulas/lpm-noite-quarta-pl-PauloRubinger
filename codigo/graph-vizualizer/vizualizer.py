from graphviz import Digraph

# Create a Digraph object
dot = Digraph(comment='Rotas')

# Define the cities and their connections
cities = {
    'Cidade do Cabo': {'Joanesburgo': 1270, 'Nairobi': 3900, 'Paris': 8900},
    'Joanesburgo': {'Cidade do Cabo': 1270, 'Nairobi': 4700, 'Mumbai': 6500},
    'Nairobi': {'Cidade do Cabo': 3900, 'Joanesburgo': 4700, 'Mumbai': 4300},
    'Paris': {'Londres': 340, 'Berlim': 1050, 'Cidade do Cabo': 8900},
    'Londres': {'Paris': 340, 'Amsterdã': 320, 'Cidade do Cabo': 8900},
    'Amsterdã': {'Londres': 320, 'Paris': 430, 'Berlim': 620},
    'Berlim': {'Amsterdã': 620, 'Paris': 1050},
    'Tóquio': {'Pequim': 2400, 'Mumbai': 6800},
    'Pequim': {'Tóquio': 2400, 'Mumbai': 3700, 'Bangcoc': 2700},
    'Mumbai': {'Pequim': 3700, 'Tóquio': 6800, 'Nairobi': 4300},
    'Bangcoc': {'Pequim': 2700, 'Mumbai': 4300, 'Joanesburgo': 8800},
}

# Add nodes and edges to the graph
for source, destinations in cities.items():
    dot.node(source)
    for destination, distance in destinations.items():
        dot.edge(source, destination, label=str(distance))

# Save the graph as PNG
dot.format = 'png'
dot.render('Rotas', view=False)

print('Grafo salvo: {Rotas.png}')