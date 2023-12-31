export VAULT_TOKEN=root
export VAULT_ADDR='http://127.0.0.1:8200'

echo 'path "secret/ms-users" {
  capabilities = ["create", "read", "update", "delete", "list"]
}
path "secret/application" {
  capabilities = ["create", "read", "update", "delete", "list"]
}
path "transit/decrypt/user" {
  capabilities = ["update"]
}
path "transit/encrypt/user" {
  capabilities = ["update"]
}

path "sys/renew/*" {
  capabilities = ["update"]
}' | vault policy write users -

#Mount transit backend
vault secrets enable transit

vault kv put secret/ms-users external-api.apiKey=Liam

#Create transit key
vault write -f transit/keys/users