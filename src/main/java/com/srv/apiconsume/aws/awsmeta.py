import boto3

# Initialize the API Gateway client
client = boto3.client('apigateway')

# Retrieve the list of REST APIs
response = client.get_rest_apis()

# Iterate through the APIs and fetch their configurations
for api in response['items']:
    api_id = api['id']
    api_name = api['name']
    # Fetch the endpoint configuration
    endpoint_config = client.get_rest_api(restApiId=api_id)['endpointConfiguration']
    print(f"API Name: {api_name}, Endpoint Configuration: {endpoint_config}")
