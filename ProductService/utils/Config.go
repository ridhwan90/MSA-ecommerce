package utils

type Configuration struct {
	Database DatabaseSetting
	Server   ServerSetting
	App      Application
}

type DatabaseSetting struct {
	Url            string
	DbName         string
	CollectionName string
}

type ServerSetting struct {
	Port string
}

type Application struct {
	Name string
}
