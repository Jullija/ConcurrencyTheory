import pandas as pd

# Load the CSV file into a DataFrame
df = pd.read_csv('mandelbrot.csv')

# Group the DataFrame by every 10 lines and calculate average and standard deviation for each group
result = df.groupby(df.index // 10).agg({'time': ['mean', 'std'], 'threads': 'first', 'tasks': 'first'})

result[('time', 'mean')] /= 1e9
result[('time', 'std')] /= 1e9

# Create a new DataFrame for the final result
final_result = pd.DataFrame({
    'threads': result['threads']['first'],
    'tasks': result['tasks']['first'],
    'average [s]': result['time']['mean'],
    'deviation': result['time']['std']
})

# Save the final result to a new CSV file
final_result.to_csv('mandelbrot_final.csv', index=False)