
import firebase_admin
from firebase_admin import credentials


default_app = firebase_admin.initialize_app()

cred = credentials.Certificate("path/to/serviceAccountKey.json")
firebase_admin.initialize_app(cred)
