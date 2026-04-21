import plotly.graph_objects as go
import pandas as pd

data = pd.read_csv('shooter_speed_history.csv')
current_speed = data['current_speed']
target_speed = data['target_speed']

fig = go.Figure()
fig.add_trace(go.Scatter(y=target_speed, name='Target Speed', line=dict(color='red')))
fig.add_trace(go.Scatter(y=current_speed, name='Current Speed', line=dict(color='blue')))
fig.show()