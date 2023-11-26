import pandas as pd
import matplotlib.pyplot as plt

# Replace "your_data.csv" with the actual path to your CSV file
file_path = "data.csv"

# Read data from CSV
df = pd.read_csv(file_path)

# Group by 'N' and 'type' and calculate the average time
grouped_data = df.groupby(['N', 'type']).mean().reset_index()

# Plot the results
for index, group in grouped_data.groupby('type'):
    plt.plot(group['N'], group['time'], marker='o', linestyle='', label=f'Type {index}')


plt.xlabel('N')
plt.ylabel('Average Time')
plt.yscale('log')
plt.legend()
plt.show()
