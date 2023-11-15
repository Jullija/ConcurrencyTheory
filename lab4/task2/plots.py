import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('times2.csv')

grouped = df.groupby(['function', 'bufferSize', 'P+K'])

functions_names = {"get2cond": "2 conditions, consume time",
                   "put2cond": "2 conditions, produce time",
                   "get4condbool": "4 conditions boolean, consume time",
                   "put4condbool": "4 conditions boolean, produce time",
                   "get4condwaiters": "4 conditions hasWaiters(), consume time",
                   "put4condwaiters": "4 conditions hasWaiters(), produce time"}
for group_name, group_data in grouped:
    function, bufferSize, pk = group_name
    plt.figure(figsize=(8, 6))
    plt.bar(group_data['idx'], group_data['time'])
    plt.title(f'Function: {functions_names[function]}, Buffer Size: {bufferSize}, P{pk}+K{pk}')
    plt.xlabel('Produce / consume chunk size')
    plt.ylabel('Time [ns]')
    plt.grid(True)

    file_name = f'plots/plot_{function}_{bufferSize}_{pk}.jpg'
    plt.savefig(file_name)
