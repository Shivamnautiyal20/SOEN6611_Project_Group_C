from scipy.stats import spearmanr
import matplotlib.pyplot.scatter as plt
import pandas as pd

results = pd.read_csv("")
#First result
first_result = results["First Metric"]
#Second result
second_result = results["Second Metric"]
#Third result
third_result = results["Third Metric"]
print(spearmanr(first_result, third_result))

third_result_g,first_result_g = zip(*sorted(zip(third_result,first_result)))
plt.plot(third_result_g, first_result_g)
plt.ylabel("First Metric result")
plt.xlabel("Third Metric result")
plt.title("First Metric vs Third Metric")
plt.show()

print(spearmanr(second_result, third_result))

third_result_g,second_result_g = zip(*sorted(zip(third_result,second_result)))
plt.plot(third_result_g, second_result_g)
plt.ylabel("Second Metric result")
plt.xlabel("Third Metric result")
plt.title("Second Metric vs Third Metric")
plt.show()